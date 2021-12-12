package org.example;

import org.example.dao.AuthorDao;
import org.example.dao.EntityDao;
import org.example.dao.MovieDao;
import org.example.model.Author;
import org.example.model.Movie;

public class App
{
    public static void main( String[] args ) throws InterruptedException {
        HibernateFactory hibernateFactory = new HibernateFactory();
        MovieDao movieDao = new MovieDao(hibernateFactory);
        AuthorDao authorDao = new AuthorDao(hibernateFactory);

        hibernateFactory.getSessionFactory().close();

        Movie psy = new Movie();
        psy.setTitle("Psy");
        psy.setProductionYear(1992);
        psy.setTime(123342334);
        psy.setType("Komedia");
        movieDao.add(psy);

        Author kulfon = new Author();
        kulfon.setName("kulfon");
        kulfon.setSurname("kulfon");
        kulfon.setAge(33);
        kulfon.setNationality("Poland");
        authorDao.add(kulfon);

        EntityDao<Movie> genericMovieDao = new EntityDao<>(hibernateFactory, Movie.class);
        EntityDao<Author> genericAuthorDao = new EntityDao<>(hibernateFactory, Author.class);

        Movie psyII = new Movie();
        psyII.setTitle("Psy II");
        psyII.setType("Dramat");
        genericMovieDao.save(psyII);

        Author walaszek = new Author();
        walaszek.setName("Bartek");
        walaszek.setSurname("Walaszek");
        walaszek.setAge(35);
        walaszek.setNationality("Poland");
        genericAuthorDao.save(walaszek);


        System.out.println("Sample movie get: ");
        System.out.println(genericMovieDao.getById(9));
        System.out.println("Sample author get: ");
        System.out.println(genericAuthorDao.getById(7));



        kulfon.setName("New author name");
        genericAuthorDao.update(kulfon);
        System.out.println("After update " + genericAuthorDao.getById(kulfon.getId()));


        hibernateFactory.getSessionFactory().close();
        Thread.sleep(2000);
    }
}
