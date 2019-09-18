package com.example.kroasiden20.ui.events;

class Event {

    // Member variables representing the title and information about the sport.
    private String title;
    private String info;
    private final int imageResource;


     Event(String title, String info, int imageResource) {
        this.title = title;
        this.info = info;
        this.imageResource = imageResource;
    }


    String getTitle() {
        return title;
    }


    String getInfo() {
        return info;
    }

    public int getImageResource() {
        return imageResource;
    }
}
