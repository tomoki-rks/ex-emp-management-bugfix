package jp.co.sample.emp_management.form;

import org.springframework.web.multipart.MultipartFile;

/**
 * 従業員を追加するフォーム.
 * 
 * @author tomoki.hirobe
 */
public class InsertEmployeeForm {

    /** 従業員名 */
    private String name;
    /** 画像 */
    private MultipartFile image;
    /** 性別 */
    private String gender;
    /** 入社日 */
    private String hireDate;
    /** メールアドレス */
    private String mailAddress;
    /** 郵便番号 */
    private String zipCode;
    /** 住所 */
    private String address;
    /** 電話番号 */
    private String telephone;
    /** 給料 */
    private Integer salary;
    /** 特性 */
    private String characteristics;
    /** 扶養人数 */
    private Integer dependentsCount;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MultipartFile getImage() {
        return this.image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHireDate() {
        return this.hireDate;
    }

    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }

    public String getMailAddress() {
        return this.mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public String getZipCode() {
        return this.zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return this.telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Integer getSalary() {
        return this.salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public String getCharacteristics() {
        return this.characteristics;
    }

    public void setCharacteristics(String characteristics) {
        this.characteristics = characteristics;
    }

    public Integer getDependentsCount() {
        return this.dependentsCount;
    }

    public void setDependentsCount(Integer dependentsCount) {
        this.dependentsCount = dependentsCount;
    }

    @Override
    public String toString() {
        return "{" + " name='" + getName() + "'" + ", image='" + getImage() + "'" + ", gender='" + getGender() + "'"
                + ", hireDate='" + getHireDate() + "'" + ", mailAddress='" + getMailAddress() + "'" + ", zipCode='"
                + getZipCode() + "'" + ", address='" + getAddress() + "'" + ", telephone='" + getTelephone() + "'"
                + ", salary='" + getSalary() + "'" + ", characteristics='" + getCharacteristics() + "'"
                + ", dependentsCount='" + getDependentsCount() + "'" + "}";
    }

}
