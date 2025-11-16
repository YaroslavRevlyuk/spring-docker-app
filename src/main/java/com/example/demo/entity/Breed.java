package com.example.demo.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "beeds")
public class Breed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "breed_name")
    private String breedName;
    @Column(name = "standard_color")
    private String standardColor;
    @Column(name = "standard_weight")
    private float standardWeight;
    public Breed(){}
    public Breed(String breedName, String standardColor, float standardWeight)
    {
        this.breedName = breedName;
        this.standardColor = standardColor;
        this.standardWeight = standardWeight;
    }
    public Long getId()
    {
        return id;
    }
    public String getBreedName()
    {
        return breedName;
    }
    public String getStandardColor()
    {
        return standardColor;
    }
    public float getStandardWeight()
    {
        return standardWeight;
    }
    public void setId(Long id)
    {
        this.id = id;
    }
    public void setBreedName(String breedName)
    {
        this.breedName = breedName;
    }
    public void setStandardColor(String standardColor)
    {
        this.standardColor = standardColor;
    }
    public void setStandardWeight(float standardWeight)
    {
        this.standardWeight = standardWeight;
    }
    public String toString()
    {
        return "Breed{" +
            "id=" + id +
            ", breedName='" + breedName + '\'' +
            ", standardColor='" + standardColor + '\'' +
            ", standardWeight=" + standardWeight +
            '}';
    }
}
