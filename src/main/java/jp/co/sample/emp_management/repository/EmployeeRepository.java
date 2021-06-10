package jp.co.sample.emp_management.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.emp_management.domain.Employee;

/**
 * employeesテーブルを操作するリポジトリ.
 * 
 * @author igamasayuki
 * 
 */
@Repository
public class EmployeeRepository {

	/**
	 * Employeeオブジェクトを生成するローマッパー.
	 */
	private static final RowMapper<Employee> EMPLOYEE_ROW_MAPPER = (rs, i) -> {
		Employee employee = new Employee();
		employee.setId(rs.getInt("id"));
		employee.setName(rs.getString("name"));
		employee.setImage(rs.getString("image"));
		employee.setGender(rs.getString("gender"));
		employee.setHireDate(rs.getDate("hire_date"));
		employee.setMailAddress(rs.getString("mail_address"));
		employee.setZipCode(rs.getString("zip_code"));
		employee.setAddress(rs.getString("address"));
		employee.setTelephone(rs.getString("telephone"));
		employee.setSalary(rs.getInt("salary"));
		employee.setCharacteristics(rs.getString("characteristics"));
		employee.setDependentsCount(rs.getInt("dependents_count"));
		return employee;
	};

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * 従業員一覧情報を入社日順で取得します.
	 * 
	 * @return 全従業員一覧 従業員が存在しない場合はサイズ0件の従業員一覧を返します
	 */
	public Page<Employee> findAll(Pageable pageable) {
		String countSql = "SELECT count(id) FROM employees";
		int count = template.queryForObject(countSql, new MapSqlParameterSource(), Integer.class);

		String sql = "SELECT id,name,image,gender,hire_date,mail_address,zip_code,address,telephone,salary,characteristics,dependents_count FROM employees ORDER BY hire_date DESC LIMIT :limit OFFSET :offset";
		SqlParameterSource param = new MapSqlParameterSource().addValue("limit", pageable.getPageSize())
				.addValue("offset", pageable.getOffset());
		List<Employee> developmentList = template.query(sql, param, EMPLOYEE_ROW_MAPPER);

		return new PageImpl<>(developmentList, pageable, count);
	}

	/**
	 * 主キーから従業員情報を取得します.
	 * 
	 * @param id 検索したい従業員ID
	 * @return 検索された従業員情報
	 * @exception org.springframework.dao.DataAccessException 従業員が存在しない場合は例外を発生します
	 */
	public Employee load(Integer id) {
		String sql = "SELECT id,name,image,gender,hire_date,mail_address,zip_code,address,telephone,salary,characteristics,dependents_count FROM employees WHERE id=:id";

		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);

		Employee development = template.queryForObject(sql, param, EMPLOYEE_ROW_MAPPER);

		return development;
	}

	/**
	 * 名前からあいまい検索します.
	 * 
	 * @param name 検索したい名前
	 * @return 検索された従業員情報リスト
	 */
	public Page<Employee> findByName(String name, Pageable pageable) {
		String countSql = "SELECT count(id) FROM employees WHERE name LIKE :name";
		int count = template.queryForObject(countSql, new MapSqlParameterSource().addValue("name", "%" + name + "%"),
				Integer.class);

		String sql = "SELECT id,name,image,gender,hire_date,mail_address,zip_code,address,telephone,salary,characteristics,dependents_count FROM employees WHERE name LIKE :name LIMIT :limit OFFSET :offset";

		SqlParameterSource param = new MapSqlParameterSource().addValue("name", "%" + name + "%")
				.addValue("limit", pageable.getPageSize()).addValue("offset", pageable.getOffset());

		List<Employee> employeeList = template.query(sql, param, EMPLOYEE_ROW_MAPPER);

		return new PageImpl<>(employeeList, pageable, count);

	}

	/**
	 * 従業員情報を変更します.
	 */
	public void update(Employee employee) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(employee);

		String updateSql = "UPDATE employees SET dependents_count=:dependentsCount WHERE id=:id";
		template.update(updateSql, param);
	}

	/**
	 * 従業員を登録します.
	 * 
	 * @param employee 登録する従業員情報
	 */
	public void insert(Employee employee) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(employee);
		int id = template.queryForObject("SELECT MAX(id) FROM employees", param, Integer.class);
		employee.setId(id + 1);

		String insertSql = "INSERT INTO employees VALUES (:id, :name, :image, :gender, :hireDate, :mailAddress, :zipCode, :address, :telephone, :salary, :characteristics, :dependentsCount);";

		template.update(insertSql, param);
	}
}
