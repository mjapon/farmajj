/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jj.controller;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import jj.entity.Clientes;
import jj.entity.Detallesfact;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import jj.controller.exceptions.IllegalOrphanException;
import jj.controller.exceptions.NonexistentEntityException;
import jj.entity.Facturas;

/**
 *
 * @author mjapon
 */
public class FacturasJpaController extends BaseJpaController implements Serializable {

    public FacturasJpaController(EntityManager em) {
        super(em);
    }

    public void create(Facturas facturas) {
        if (facturas.getDetallesfactList() == null) {
            facturas.setDetallesfactList(new ArrayList<Detallesfact>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Clientes cliId = facturas.getCliId();
            if (cliId != null) {
                cliId = em.getReference(cliId.getClass(), cliId.getCliId());
                facturas.setCliId(cliId);
            }
            List<Detallesfact> attachedDetallesfactList = new ArrayList<Detallesfact>();
            for (Detallesfact detallesfactListDetallesfactToAttach : facturas.getDetallesfactList()) {
                detallesfactListDetallesfactToAttach = em.getReference(detallesfactListDetallesfactToAttach.getClass(), detallesfactListDetallesfactToAttach.getDetfId());
                attachedDetallesfactList.add(detallesfactListDetallesfactToAttach);
            }
            facturas.setDetallesfactList(attachedDetallesfactList);
            em.persist(facturas);
            if (cliId != null) {
                cliId.getFacturasList().add(facturas);
                cliId = em.merge(cliId);
            }
            for (Detallesfact detallesfactListDetallesfact : facturas.getDetallesfactList()) {
                Facturas oldFactIdOfDetallesfactListDetallesfact = detallesfactListDetallesfact.getFactId();
                detallesfactListDetallesfact.setFactId(facturas);
                detallesfactListDetallesfact = em.merge(detallesfactListDetallesfact);
                if (oldFactIdOfDetallesfactListDetallesfact != null) {
                    oldFactIdOfDetallesfactListDetallesfact.getDetallesfactList().remove(detallesfactListDetallesfact);
                    oldFactIdOfDetallesfactListDetallesfact = em.merge(oldFactIdOfDetallesfactListDetallesfact);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Facturas facturas) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Facturas persistentFacturas = em.find(Facturas.class, facturas.getFactId());
            Clientes cliIdOld = persistentFacturas.getCliId();
            Clientes cliIdNew = facturas.getCliId();
            List<Detallesfact> detallesfactListOld = persistentFacturas.getDetallesfactList();
            List<Detallesfact> detallesfactListNew = facturas.getDetallesfactList();
            List<String> illegalOrphanMessages = null;
            for (Detallesfact detallesfactListOldDetallesfact : detallesfactListOld) {
                if (!detallesfactListNew.contains(detallesfactListOldDetallesfact)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detallesfact " + detallesfactListOldDetallesfact + " since its factId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (cliIdNew != null) {
                cliIdNew = em.getReference(cliIdNew.getClass(), cliIdNew.getCliId());
                facturas.setCliId(cliIdNew);
            }
            List<Detallesfact> attachedDetallesfactListNew = new ArrayList<Detallesfact>();
            for (Detallesfact detallesfactListNewDetallesfactToAttach : detallesfactListNew) {
                detallesfactListNewDetallesfactToAttach = em.getReference(detallesfactListNewDetallesfactToAttach.getClass(), detallesfactListNewDetallesfactToAttach.getDetfId());
                attachedDetallesfactListNew.add(detallesfactListNewDetallesfactToAttach);
            }
            detallesfactListNew = attachedDetallesfactListNew;
            facturas.setDetallesfactList(detallesfactListNew);
            facturas = em.merge(facturas);
            if (cliIdOld != null && !cliIdOld.equals(cliIdNew)) {
                cliIdOld.getFacturasList().remove(facturas);
                cliIdOld = em.merge(cliIdOld);
            }
            if (cliIdNew != null && !cliIdNew.equals(cliIdOld)) {
                cliIdNew.getFacturasList().add(facturas);
                cliIdNew = em.merge(cliIdNew);
            }
            for (Detallesfact detallesfactListNewDetallesfact : detallesfactListNew) {
                if (!detallesfactListOld.contains(detallesfactListNewDetallesfact)) {
                    Facturas oldFactIdOfDetallesfactListNewDetallesfact = detallesfactListNewDetallesfact.getFactId();
                    detallesfactListNewDetallesfact.setFactId(facturas);
                    detallesfactListNewDetallesfact = em.merge(detallesfactListNewDetallesfact);
                    if (oldFactIdOfDetallesfactListNewDetallesfact != null && !oldFactIdOfDetallesfactListNewDetallesfact.equals(facturas)) {
                        oldFactIdOfDetallesfactListNewDetallesfact.getDetallesfactList().remove(detallesfactListNewDetallesfact);
                        oldFactIdOfDetallesfactListNewDetallesfact = em.merge(oldFactIdOfDetallesfactListNewDetallesfact);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = facturas.getFactId();
                if (findFacturas(id) == null) {
                    throw new NonexistentEntityException("The facturas with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Facturas facturas;
            try {
                facturas = em.getReference(Facturas.class, id);
                facturas.getFactId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The facturas with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Detallesfact> detallesfactListOrphanCheck = facturas.getDetallesfactList();
            for (Detallesfact detallesfactListOrphanCheckDetallesfact : detallesfactListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Facturas (" + facturas + ") cannot be destroyed since the Detallesfact " + detallesfactListOrphanCheckDetallesfact + " in its detallesfactList field has a non-nullable factId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Clientes cliId = facturas.getCliId();
            if (cliId != null) {
                cliId.getFacturasList().remove(facturas);
                cliId = em.merge(cliId);
            }
            em.remove(facturas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Facturas> findFacturasEntities() {
        return findFacturasEntities(true, -1, -1);
    }

    public List<Facturas> findFacturasEntities(int maxResults, int firstResult) {
        return findFacturasEntities(false, maxResults, firstResult);
    }

    private List<Facturas> findFacturasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Facturas.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Facturas findFacturas(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Facturas.class, id);
        } finally {
            em.close();
        }
    }

    public int getFacturasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Facturas> rt = cq.from(Facturas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
