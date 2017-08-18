package com.example.demo;


import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.HashSet;
import java.util.Set;

@Controller
public class HomeController {
    @Autowired
    DirectorRepository directorRepository;

    @RequestMapping("/")
    public String index(Model model){
        //first create adirector
        Director director = new Director();
        director.setName("Stephen Bullock");
        director.setGenre("Sci Fi");

        //Now lets create a movie
        Movie movie = new Movie();
        movie.setTitle("Star Movie");
        movie.setYear(2017);
        movie.setDescription("Abot Stars...");

        //add the movie to an empty list
        Set<Movie> movies = new HashSet<Movie>();
        movies.add(movie);
        movie = new Movie();
        movie.setTitle("DeathStart Eworks");
        movie.setYear(2011);
        movie.setDescription("About Ewoks on the Desth Start...");
        movies.add(movie);

        //add the list of movies to the directory movie list
        director.setMovies(movies);

        //save the director to the database
        directorRepository.save(director);

        //grad all the directors from the databse andd send them to
        //the template
        model.addAttribute("directors", directorRepository.findAll());
        return "index";
    }
}
