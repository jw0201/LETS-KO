package dd2.local.busi.company.service.dao;

import dd2.com.dao.GenericHibernateDAO;
import dd2.com.jqgrid.*;
import dd2.local.entity.AdCompanyEntity;
import dd2.local.entity.SampleEntity;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.criterion.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: KDJ
 * Date: 13. 11. 11
 * Time: 오후 6:37
 * To change this template use File | Settings | File Templates.
 */
@SuppressWarnings("unchecked")
@Repository
public class CompanyDAOHibernate extends GenericHibernateDAO<AdCompanyEntity, Long> implements CompanyDAO {
    private static final Log logger = LogFactory.getLog(CompanyDAOHibernate.class);

    @Override
    public List<Map<String, Object>> list(JqGridRequest request) {
        StringBuffer sbQuery = new StringBuffer();
        sbQuery
                .append(" SELECT CONVERT(VARCHAR(29), A.AD_Company_ID)  AS id               ")
                .append("      , A.AD_Company_ID                        AS adCompanyId      ")
                .append("      , A.isActive                             AS isActive         ")
                .append("      , A.Created                              AS created          ")
                .append("      , A.CreatedBy                            AS createdBy        ")
                .append("      , A.Description                          AS description      ")
                .append("      , A.Name                                 AS name             ")
                .append("      , A.Updated                              AS updated          ")
                .append("      , A.UpdatedBy                            AS updatedBy        ")
                .append("      , A.Value                                AS value            ")
                .append("      , B.Name                                 AS createdByName    ")
                .append("      , C.Name                                 AS updatedByName    ")
                .append("   FROM AD_Company A                                               ")
                .append("        LEFT JOIN AD_User B                                        ")
                .append("             ON   B.AD_User_ID    = A.CreatedBy                    ")
                .append("        LEFT JOIN AD_User C                                        ")
                .append("             ON   C.AD_User_ID    = A.UpdatedBy                    ")
        ;

        JqGridQueryBuilder jqGridQueryBuilder = new JqGridQueryBuilder(sbQuery, request);
        return this.getSession()
                .createSQLQuery(jqGridQueryBuilder.toString())
                .setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP)
                .list();
    }

    @Override
    public JqGridResponseGeneric<AdCompanyEntity> listForHibernate(JqGridRequest request) {
        JqGridResponseGeneric<AdCompanyEntity> response = new JqGridResponseGeneric<>();

        Criterion criterion = JqGridRestrictionForHibernate.create(AdCompanyEntity.class, request);
        Order order = JqGridOrderForHibernate.create(request);

        Criteria rowCriteria = this.getCriteria()
                .setProjection(Projections.projectionList()
                        .add(Projections.rowCount())
                );
        if ( criterion != null ) {
            rowCriteria = rowCriteria.add(criterion);
        }
        if ( order != null ) {
            rowCriteria = rowCriteria.addOrder(order);
        }
        int total = Integer.parseInt(rowCriteria.uniqueResult().toString());


        Criteria listCriteria = this.getCriteria();
        if (criterion != null) {
            listCriteria = listCriteria.add(criterion);
        }
        if (order != null) {
            listCriteria = listCriteria.addOrder(order);
        }
        List<AdCompanyEntity> entityList = listCriteria
                .setFirstResult(request.getPageNumber() - 1)
                .setMaxResults(request.getPageSize())
                .list();

        response.setTotal(total);
        response.setPage(request.getPageNumber());
        response.setRecords(request.getPageSize());
        response.setRows(entityList);

        return response;
    }

    @Override
    public List<Map<String, Object>> jqGridSelect() {
        List result = this.getCriteria()
                .add(Restrictions.eq("this.isActive", "Y"))
                .setProjection(Projections.projectionList()
                        .add(Projections.property("this.adCompanyId").as("adCompanyId"))
                        .add(Projections.property("this.name").as("name"))
                        .add(Projections.property("this.isActive").as("isActive"))
                )
                .setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP)
                .list();
        return result;
    }
}
