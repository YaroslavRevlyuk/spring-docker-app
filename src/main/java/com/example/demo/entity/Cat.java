package com.example.demo.entity;
/* 
import org.hibernate.internal.build.AllowNonPortable;

import jakarta.annotation.sql.DataSourceDefinition;
*/
import jakarta.persistence.*;

@Entity // Говорим Spring: "Это сущность БД"
@Table(name = "cats") // Имя таблицы в базе данных
public class Cat {
    // Поля класса (как столбцы в таблице)
    @Id //даём статус ключа
    @GeneratedValue(strategy = GenerationType.IDENTITY) // генерируем ключ
    private Long id;        // уникальный номер
    @Column(name = "name") //задаём имя полю
    private String name;    // имя кота
    @Column(name = "age")
    private int age;        // возраст
    @Column(name = "weight")
    private int weight;     // вес
    @ManyToOne(fetch = FetchType.LAZY)//указываем связь и вид потгрузки (есть такая, а ещё fetch = FetchType.EAGER, что будет подгружать все данные)
    @JoinColumn(name = "breed_id")// указываем имя поля внешнего ключа
    private Breed breed;

    // Конструктор по умолчанию (ОБЯЗАТЕЛЬНЫЙ)
    public Cat() {
    }

    // Конструктор с параметрами
    public Cat(String name, int age, int weight) {
        this.name = name;
        this.age = age;
        this.weight = weight;
    }

    // ГЕТТЕРЫ (методы для чтения значений)
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getWeight() {
        return weight;
    }

    // СЕТТЕРЫ (методы для установки значений)
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
    public String toString() {
        return "Cat{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", age=" + age +
                ", breed=" + (breed != null ? breed.getBreedName() : "null") +
                '}';
    }
}