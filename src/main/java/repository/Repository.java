package repository;

import model.*;
import repository.entities.UserPO;
import resources.dto.DTOSignUp;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;

@Dependent
@Transactional
public class Repository {

    private final EntityManager entityManager;

    @Inject
    public Repository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public User createUser(DTOSignUp dtoSignUp){
        UserPO userPO = new UserPO(dtoSignUp.getEmail().getValue(), dtoSignUp.getUserName().getValue(), dtoSignUp.getPassword().getValue());
        entityManager.persist(userPO);
        return userPO.toUser();
    }

    public User findUser(Email email, UserName userName, Password password){
        try {
            UserPO userPO = entityManager
                    .createNamedQuery(UserPO.FIND_BY_EMAIL, UserPO.class)
                    .setParameter(UserPO.EMAIL_PARAM, email == null ? "" : email.getValue())
                    .setParameter(UserPO.USERNAME_PARAM, userName == null ? "" : userName.getValue())
                    .getSingleResult();
            if(!password.getValue().equals(userPO.getPassword())){
                return null;
            }
            return userPO.toUser();
        } catch (NoResultException e){
            return null;
        }
    }




}
