//package pl.mecinapatrycja.dto;
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.transaction.Transactional;
//public class asa {
//    @PersistenceContext
//    private EntityManager em;
//
//    @Transactional
//    public void doSomething()​
//
//    {
//        Person person1 = em.find(Person.class, 1);
//        person1.name = "Adam";
//        Person person2 = em.find(Person.class, 1);
//        person2.name = "Janusz";
//        em.persist(person1);
//        System.out.println(person1.name);
//        System.out.println(person2.name);
//    }
//}
