package com.example.kroasiden20.ui.volunteer;

class Volunteer {

    // Member variables representing the title and information about the sport.
    private String name;
    private String role;
    private String email;
    private String phone;
    private String lastVol;
    private final int imageResource1;
    private final int imageResource2;


    Volunteer(String name, String role, String email, String phone, String lastVol, int imageResource1, int imageResource2) {
        this.name = name;
        this.role = role;
        this.email = email;
        this.phone = phone;
        this.lastVol = lastVol;
        this.imageResource1 = imageResource1;
        this.imageResource2 = imageResource2;
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


    public int getImageResource1() {
        return imageResource1;
    }
    public int getImageResource2() {
        return imageResource2;
    }

}
