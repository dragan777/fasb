package com.fasb.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
public class Customer implements Serializable {




        @Id
        @GeneratedValue
        private Long id;

        @NotNull
        private String firstName;

        @NotNull
        private String lastName;

        @NotNull
        private String address;

        private int creditClass = 2;

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getFirstName() {
                return firstName;
        }

        public void setFirstName(String firstName) {
                this.firstName = firstName;
        }

        public String getLastName() {
                return lastName;
        }

        public void setLastName(String lastName) {
                this.lastName = lastName;
        }

        public String getAddress() {
                return address;
        }

        public void setAddress(String address) {
                this.address = address;
        }

        public int getCreditClass() {
                return creditClass;
        }

        public void setCreditClass(int creditClass) {
                this.creditClass = creditClass;
        }
}
