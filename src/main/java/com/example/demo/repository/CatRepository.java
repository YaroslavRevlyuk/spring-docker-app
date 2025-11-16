package com.example.demo.repository;
import com.example.demo.entity.Cat;
import java.util.List;
import java.util.Optional;
import jakarta.transaction.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository // Говорим Spring: "Это репозиторий для работы с БД"
/* 
public interface CatRepository extends JpaRepository<Cat, Long> {
    // Всё! Spring сам создаст реализацию с методами:
    // - save(cat) - сохранить кота в БД
    // - findAll() - найти всех котов
    // - findById(id) - найти кота по id
    // - deleteById(id) - удалить кота
}
*/
public class CatRepository{
    @PersistenceContext  // Spring: "Вставь сюда менеджер сущностей"/как я понял, служит для идентифекации следующей строчки
    private EntityManager entityManager;//EntityManager - это интерфейс. 

    public List<Cat> findAll() {
        String jpql = "SELECT c FROM Cat c";//создание sql запроса
        TypedQuery<Cat> query = entityManager.createQuery(jpql, Cat.class);//TypedQuery  - интерфейс для запросов
        return query.getResultList();//из jakarta.persistence.TypedQuery, возвращает результат, который определяется в предыдущей строчки
    }

    public Optional<Cat> findById(Long id) {//ищет по id
    Cat cat = entityManager.find(Cat.class, id);//встроенный метод, он прошерстит сущность
    return Optional.ofNullable(cat);//вернёт не null
    }

    @Transactional//необходима для транзакций/общения с бд
    public Cat save(Cat cat) {//сохраняет/обнавляет данные
        if (cat.getId() == null) {
            // Новый кот - добавляем в БД
            entityManager.persist(cat);
            return cat;
        } else {
            // Существующий кот - обновляем
            return entityManager.merge(cat);
        }

    }

    @Transactional
    public boolean deleteById(Long id) {
        Cat cat = entityManager.find(Cat.class, id);
        if (cat != null) {
            entityManager.remove(cat);  // DELETE
            return true;
        }
        return false;
    }

    public List<Cat> findByName(String name)
    {
        String jpql = "SELECT c FROM c Cat WHERE c.name=:name";
        TypedQuery<Cat> query = entityManager.createQuery(jpql, Cat.class);
        query.setParameter("name", name);//первое название объекта посе :, а второе переменная передаваемая на вход
        return query.getResultList();
    }

    public List<Cat> findByAge(int age)
    {
        String jpql = "SELECT c FROM c Cat WHERE c.age>:age";
        TypedQuery<Cat> query = entityManager.createQuery(jpql, Cat.class);
        query.setParameter("age", age);
        return query.getResultList();
    }
}