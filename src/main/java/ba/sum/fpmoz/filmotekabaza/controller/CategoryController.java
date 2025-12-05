package ba.sum.fpmoz.filmotekabaza.controller;

import ba.sum.fpmoz.filmotekabaza.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/categories")
class CategoryController {
    @Autowired
    CategoryRepository categoryRepository;
}
