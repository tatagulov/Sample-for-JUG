package io.tatagulov.badproject.web.repo;

import io.tatagulov.badproject.web.dto.flight.FlightListRequest;
import io.tatagulov.badproject.web.entity.AirportsEntity;
import io.tatagulov.badproject.web.entity.FlightsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.LinkedList;
import java.util.List;

@Repository
public class FlightRepository extends CrudRepository<FlightsEntity,Integer> {

    private final AirportRepository airportRepository;

    @Autowired
    public FlightRepository(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    @Override
    public Class<FlightsEntity> getType() {
        return FlightsEntity.class;
    }

    public List<FlightsEntity> list(FlightListRequest request) {
        int offSet = request.getOffset();
        int limit = request.getLimit();

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<FlightsEntity> q = cb.createQuery(FlightsEntity.class);
        Root<FlightsEntity> c = q.from(FlightsEntity.class);
        List<Predicate> predicates = new LinkedList<>();

        if (request.getArrivalAirport()!=null) {
            AirportsEntity arrivalAirport = airportRepository.findById(request.getArrivalAirport());
            predicates.add(cb.equal(c.get("airportsByArrivalAirport"),arrivalAirport));
        }
        if (request.getDepartureAirport()!=null) {
            AirportsEntity departureAirport = airportRepository.findById(request.getDepartureAirport());
            predicates.add(cb.equal(c.get("airportsByDepartureAirport"),departureAirport));
        }

        if (request.getFlightNo()!=null) {
            predicates.add(cb.equal(c.get("flightNo"),request.getFlightNo()));
        }

        if (request.getStatus()!=null) {
            predicates.add(cb.equal(c.get("status"),request.getStatus()));
        }

        q.select(c).where(predicates.toArray(new Predicate[predicates.size()]));
        return entityManager.createQuery(q).setFirstResult(offSet).setMaxResults(limit).getResultList();
    }

    public List<FlightsEntity> listJoinFetchTicketFlight() {
        return entityManager.createNamedQuery("Flight.findAllJoin").getResultList();
    }
}
