package io.sundr.jakarta.templates;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

@Path("/templates")
public class TemplateEndpoint {

  @Inject
  private EntityManager entityManager;

  @GET
  public List<Target> listAll() {
    return entityManager.createQuery("SELECT e FROM Target e", Target.class).getResultList();
  }

  @GET
  @Path("{id}")
  public Target getById(Long id) {
    Target entity = entityManager.find(Target.class, id);
    if (entity == null) {
      throw new WebApplicationException("Target with id of " + id + " does not exist.",
          Response.Status.NOT_FOUND.getStatusCode());
    }
    return entity;
  }

  @POST
  @Transactional
  public Response create(Target entity) {
    if (entity.getId() != null) {
      throw new WebApplicationException("Id was invalidly set on request.", 422);
    }
    entityManager.persist(entity);
    return Response.ok(entity).status(Response.Status.CREATED.getStatusCode()).build();
  }

  @DELETE
  @Path("{id}")
  @Transactional
  public Response delete(Long id) {
    Target entity = entityManager.getReference(Target.class, id);
    if (entity == null) {
      throw new WebApplicationException("Target with id of " + id + " does not exist.",
          Response.Status.NOT_FOUND.getStatusCode());
    }
    entityManager.remove(entity);
    return Response.status(204).build();
  }

  @DELETE
  @Path("{id}")
  @Transactional
  public Response update(Long id, Target entity) {
    Target existing = entityManager.find(Target.class, id);

    if (existing == null) {
      throw new WebApplicationException("Target with id of " + id + " does not exist.",
          Response.Status.NOT_FOUND.getStatusCode());
    }
    copy(entity, existing);
    return Response.accepted(existing).build();
  }

  public void copy(Target from, Target to) {
  }
}
