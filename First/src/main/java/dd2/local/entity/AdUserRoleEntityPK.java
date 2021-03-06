package dd2.local.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: KDJ
 * Date: 13. 11. 11
 * Time: 오전 10:03
 * To change this template use File | Settings | File Templates.
 */
public class AdUserRoleEntityPK implements Serializable {
    private Long adUserId;
    private Long adRoleId;
    private Long adCompanyId;

    @Id
    @Column(name = "AD_User_ID")
    public Long getAdUserId() {
        return adUserId;
    }
    public void setAdUserId(Long adUserId) {
        this.adUserId = adUserId;
        }

    @Id
    @Column(name = "AD_Role_ID")
    public Long getAdRoleId() {
        return adRoleId;
    }
    public void setAdRoleId(Long adRoleId) {
        this.adRoleId = adRoleId;
    }

    @Id
    @Column(name = "AD_Company_ID")
    public Long getAdCompanyId() {
        return adCompanyId;
    }
    public void setAdCompanyId(Long adCompanyId) {
        this.adCompanyId = adCompanyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AdUserRoleEntityPK that = (AdUserRoleEntityPK) o;

        if (adCompanyId != null ? !adCompanyId.equals(that.adCompanyId) : that.adCompanyId != null) return false;
        if (adRoleId != null ? !adRoleId.equals(that.adRoleId) : that.adRoleId != null) return false;
        if (adUserId != null ? !adUserId.equals(that.adUserId) : that.adUserId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = adUserId != null ? adUserId.hashCode() : 0;
        result = 31 * result + (adRoleId != null ? adRoleId.hashCode() : 0);
        result = 31 * result + (adCompanyId != null ? adCompanyId.hashCode() : 0);
        return result;
    }
}
