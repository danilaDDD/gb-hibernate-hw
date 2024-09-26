package ru.danila;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import ru.danila.entity.Post;
import ru.danila.entity.PostComment;
import ru.danila.entity.User;

import java.util.List;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.configure();

        try(SessionFactory sessionFactory = configuration.buildSessionFactory()){
            try(Session session = sessionFactory.openSession()){
                //insertData(session);
                System.out.println(getAllComments(session));
                System.out.println(findPostsByUserId(session, 2));
                System.out.println(findCommentsByUserId(session, 2L));
                // по идентификатору юзера загрузить юзеров под чьими публикациями он оставил комментарий
                System.out.println(findUserOfCommentByUserId(session, 2L));
            }
        }
    }

    private static List<User> findUserOfCommentByUserId(Session session, long userId) {
        User user = session.find(User.class, userId);
        if(user != null){
            return user.getPostComments().stream()
                    .map(PostComment::getUser).collect(Collectors.toList());
        }

        return List.of();
    }

    private static List<PostComment> findCommentsByUserId(Session session, long userId) {
        User user = session.find(User.class, userId);
        if(user != null){
            return user.getPostComments();
        }

        return List.of();
    }

    private static void insertData(Session session) {
        User user = new User(2, "ivan");
        Post post1 = new Post(3, "post3", "post3", user);
        Post post2 = new Post(4, "post4", "post4", user);
        PostComment comment = new PostComment(4, post1, "comment", user);

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