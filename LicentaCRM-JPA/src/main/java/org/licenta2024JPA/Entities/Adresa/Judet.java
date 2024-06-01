package org.licenta2024JPA.Entities.Adresa;

public enum Judet {
    ALBA("Alba"),
    ARAD("Arad"),
    ARGES("Argeș"),
    BACAU("Bacău"),
    BIHOR("Bihor"),
    BISTRITA_NASAUD("Bistrița-Năsăud"),
    BOTOSANI("Botoșani"),
    BRASOV("Brașov"),
    BRAILA("Brăila"),
    BUZAU("Buzău"),
    CARAS_SEVERIN("Caraș-Severin"),
    CALARASI("Călărași"),
    CLUJ("Cluj"),
    CONSTANTA("Constanța"),
    COVASNA("Covasna"),
    DAMBOVITA("Dâmbovița"),
    DOLJ("Dolj"),
    GALATI("Galați"),
    GIURGIU("Giurgiu"),
    GORJ("Gorj"),
    HARGHITA("Harghita"),
    HUNEDOARA("Hunedoara"),
    IALOMITA("Ialomița"),
    IASI("Iași"),
    ILFOV("Ilfov"),
    MARAMURES("Maramureș"),
    MEHEDINTI("Mehedinți"),
    MURES("Mureș"),
    NEAMT("Neamț"),
    OLT("Olt"),
    PRAHOVA("Prahova"),
    SATU_MARE("Satu Mare"),
    SALAJ("Sălaj"),
    SIBIU("Sibiu"),
    SUCEAVA("Suceava"),
    TELEORMAN("Teleorman"),
    TIMIS("Timiș"),
    TULCEA("Tulcea"),
    VASLUI("Vaslui"),
    VALCEA("Vâlcea"),
    VRANCEA("Vrancea"),
    BUCURESTI("București");

    private final String name;

    Judet(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}