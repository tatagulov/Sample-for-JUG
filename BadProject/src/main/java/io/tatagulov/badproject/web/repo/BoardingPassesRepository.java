package io.tatagulov.badproject.web.repo;

import io.tatagulov.badproject.web.dto.boardingPasses.BoardingPassesListRequest;
import io.tatagulov.badproject.web.entity.BoardingPassesEntity;
import io.tatagulov.badproject.web.entity.TicketFlightsEntityPK;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.LinkedList;
import java.util.List;

@Repository
public class BoardingPassesRepository extends CrudRepository<BoardingPassesEntity,TicketFlightsEntityPK> {

    @Override
    public Class<BoardingPassesEntity> getType() {
        return BoardingPassesEntity.class;
    }

    public List<BoardingPassesEntity> list(BoardingPassesListRequest request) {
        int offSet = request.getOffset();
        int limit = request.getLimit();

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<BoardingPassesEntity> q = cb.createQuery(BoardingPassesEntity.class);
        Root<BoardingPassesEntity> c = q.from(BoardingPassesEntity.class);
        List<Predicate> predicates = new LinkedList<>();
        if (request.getBoardingNo()!=null) {
            predicates.add(cb.equal(c.get("boardingNo"),request.getBoardingNo()));
        }
        q.select(c).where(predicates.toArray(new Predicate[predicates.size()]));
        return entityManager.createQuery(q).setFirstResult(offSet).setMaxResults(limit).getResultList();
    }
}
