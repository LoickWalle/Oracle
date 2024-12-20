module moduleA {
    exports myPackage; // export du package myPackage a tous les autres modules
    exports mySecondPackage;
    exports myPackage.subPackage;
}