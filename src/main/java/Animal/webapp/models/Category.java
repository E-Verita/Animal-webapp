package Animal.webapp.models;

import lombok.*;

import javax.persistence.*;

    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    @Getter
    @Setter
    @Entity
    @Table
    public class Category {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;
        private String name;
}
