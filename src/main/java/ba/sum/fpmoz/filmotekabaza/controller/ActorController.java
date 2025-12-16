package ba.sum.fpmoz.filmotekabaza.controller;

import ba.sum.fpmoz.filmotekabaza.models.Actor;
import ba.sum.fpmoz.filmotekabaza.repositories.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/actors")
class ActorController {
    @Autowired
    ActorRepository actorRepository;

    @PostMapping
    public Actor createActor(@RequestBody Actor actor) {
        return actorRepository.save(actor);
    }

    @GetMapping
    public List<Actor> getAllActors() {
        return actorRepository.findAll();
    }

    @GetMapping("/{id}")
    public Actor getActorById(@PathVariable Integer id) {
        return actorRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Actor updateActor(@PathVariable Integer id,
                             @RequestBody Actor actor) {
        Optional<Actor> dbActor = actorRepository.findById(id);
        if (dbActor.isPresent()) {
            Actor existingActor = dbActor.get();
            existingActor.setFirstName(actor.getFirstName());
            existingActor.setLastName(actor.getLastName());
            return actorRepository.save(existingActor);
        } else {
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public void deleteActor(@PathVariable Integer id) {
        actorRepository.deleteById(id);
    }
}
