package entity;

public enum Features {

    TRAILERS("Trailers"),
    COMMENTARIES("Commentaries"),
    DELETED_SCENES("Deleted Scenes"),
    BEHIND_THE_SCENES("Behind the Scenes");
    //set('Trailers', 'Commentaries', 'Deleted Scenes', 'Behind the Scenes')

    private final String value;

    Features(String value) {
        this.value = value;
    }
}
