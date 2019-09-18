package com.example.kroasiden20.ui.volunteer;

class Volunteer {

    // Member variables representing the title and information about the sport.
    private String name;
    private String role;
    private String email;
    private String phone;
    private String lastVol;



    Volunteer(String name, String role, String email, String phone, String lastVol) {
        this.name = name;
        this.role = role;
        this.email = email;
        this.phone = phone;
        this.lastVol = lastVol;

    }


    String getName() {
        return name;
    }

    String getRole() {
        return role;
    }


    String getEmail() {
        return email;
    }

    String getPhone() { return phone; }

    String getLastVol() {
        return lastVol;
    }



}
