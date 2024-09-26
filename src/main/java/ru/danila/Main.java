package ru.danila;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import ru.danila.entity.BaseEntity;
import ru.danila.entity.Post;
import ru.danila.entity.PostComment;
import ru.danila.entity.User;

import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.configure();

        try(SessionFactory sessionFactory = configuration.buildSessionFactory()){
            try(Session session = sessionFactory.openSession()){
                //insertData(session);
                System.out.println(getAllComments(session));
                System.out.println(findPostsByUserId(session, 2));
            }
        }
    }

    private static void insertData(Session session) {
        User user = new User(2, "ivan");
        Post post1 = new Post(3, "post3", "post3", user);
        Post post2 = new Post(4, "post4", "post4", user);
        PostComment comment = new PostComment(4, post1, "comment", user);

        for(BaseEntity entity: List.of(user, post1, post2, comment))
            entity.onCreate();

        Transaction tx = session.beginTransaction();
        session.persist(user);
        session.persist(post1);
        session.persist(post2);
        session.save(comment);
        tx.commit();

    }

    private static List<Post> findPostsByUserId(Session session, long userId) {
        User user = session.find(User.class, userId);
        return user.getPosts();
    }

    private static List<PostComment> getAllComments(Session session) {
        return session.createQuery("SELECT a FROM PostComment a", PostComment.class).getResultList();
    }


}