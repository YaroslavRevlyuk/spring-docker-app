package com.example.demo.controller;

//import org.springframework.web.bind.annotation.*;
//import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.entity.Cat;
import com.example.demo.repository.CatRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@RestController// Говорим Spring: "Этот класс обрабатывает HTTP запросы"
@RequestMapping("/api/cats") // Все URL начинаются с /api/cats
public class CatController {
    @Autowired
    private CatRepository catRepository;//при нахождении чего-то класса CatRepository он присваивает это catRepository
    
    @GetMapping
    public List<Cat> getAllCats() {
        return catRepository.findAll();
    }

    @GetMapping("/{id}")//берём такую то часть URL запроса
    public ResponseEntity<Cat> getCatById(@PathVariable Long id) {//преобразовываем её в long и записываем в переменную
        Optional<Cat> cat = catRepository.findById(id); //это ссылка, а не контейнер в контейнере
        if (cat.isPresent()) {
            return ResponseEntity.ok(cat.get());//ResponseEntity.ok - для вывода, cat.get() - вытаскивает данные из контейнера
        }
        else
        {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping// Она помечает метод как обработчик для HTTP POST (Это стандартный метод HTTP, используемый для отправки данных на сервер для создания нового ресурса) запросов
    public Cat createCat(@RequestBody Cat cat) {//преобразовываем тело HTTP запроса во что-то класса Cat
        return catRepository.save(cat);
    }

    @GetMapping("/search")
    public List<Cat> getCatsByName(@RequestParam String name) {//не читаем тело, а извлекаем
        return catRepository.findByName(name);
    }

    @GetMapping("/older_than")
    public List<Cat> getCatsByAge(@RequestParam int age) {//не читаем тело, а извлекаем
        return catRepository.findByAge(age);
    }
    /* 
    /* 
    @GetMapping("/api/main")
    public String mainListener()
    {
        return "Hello World!";
    }
      
    @Autowired // Говорим Spring: "Вставь сюда репозиторий автоматически"
    private CatRepository catRepository;
    
    // GET http://localhost:8080/api/cats
    @GetMapping // Обрабатывает GET запросы
    public List<Cat> getAllCats() {
        return catRepository.findAll(); // Вернет всех котов из БД
    }
    
    // POST http://localhost:8080/api/cats
    @PostMapping // Обрабатывает POST запросы
    public Cat createCat(@RequestBody Cat cat) {
        // @RequestBody - "возьми данные из тела запроса и создай объект Cat"
        return catRepository.save(cat); // Сохраняет кота в БД
    }
    /* 
     @GetMapping
    public String test() {
        return "Работает!";
    
}
        */
}
