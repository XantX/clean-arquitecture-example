package com.example.rest_service.user.infraestructure.schemas;

import java.io.Serializable;
import java.util.List;

public class UserJsonSerializable implements Serializable {
    private List<UserJsonApi> results;

    public List<UserJsonApi> getResults() {
        return results;
    }

    public void setResults(List<UserJsonApi> results) {
        this.results = results;
    }
    public static class Login {
        private String uuid;

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }
    }
    public static class UserJsonApi {
        public UserJsonApi() {}
        private String gender;
        private NameJson name;
        private String email;
        private Login login;

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public NameJson getName() {
            return name;
        }

        public void setName(NameJson name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public Login getLogin() {
            return login;
        }

        public void setLogin(Login login) {
            this.login = login;
        }
    }
    public static class NameJson {
        private String title;
        private String first;
        private String last;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getFirst() {
            return first;
        }

        public void setFirst(String first) {
            this.first = first;
        }

        public String getLast() {
            return last;
        }

        public void setLast(String last) {
            this.last = last;
        }
    }
}
