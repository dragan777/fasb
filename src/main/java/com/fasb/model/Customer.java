package com.fasb.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
public class Customer extends BaseEntity implements Serializable {




        @NotNull
        private String firstName;

        @NotNull
        private String lastName;

        @NotNull
        private String address;

        private int creditClass = 2;


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

        public Customer(@NotNull String firstName, @NotNull String lastName, @NotNull String address, int creditClass) {
                this.firstName = firstName;
                this.lastName = lastName;
                this.address = address;
                this.creditClass = creditClass;
        }

        public Customer() {
        }
}
