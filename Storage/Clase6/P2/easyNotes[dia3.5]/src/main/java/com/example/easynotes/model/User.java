package com.example.easynotes.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "first_name", length = 40, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 40, nullable = false)
    private String lastName;

    @Column(name = "dni", length = 10, unique = true)
    private String dni;

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<Note> authorNotes = new HashSet<>();

    @OneToMany(mappedBy = "user")
    Set<Thank> thanks;

    @ManyToMany
    @JoinTable(
            name = "note_revision",
            joinColumns = @JoinColumn(name = "revisor_id"),
            inverseJoinColumns = @JoinColumn(name = "revised_note_id")
    )
    private Set<Note> revisedNotes = new HashSet<>();

    public void addRevisedNote(Note note) {
        this.revisedNotes.add(note);
        note.getRevisers().add(this);
    }

    public void removeBook(Note note) {
        this.revisedNotes.remove(note);
        note.getRevisers().remove(this);
    }

}
