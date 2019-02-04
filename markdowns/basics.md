# JPA - Les basiques

Ajouter les annotations nécessaire à la persistence du `Data Object` :

- L'identifiant doit être incrémenté automatiquement
- La propriété `computedString` ne doit pas être sauvegardée (mais elle doit être présente dans la classe)

@[Product.java]({"stubs": ["src/main/java/jee/jpa/Product.java"], "command": "jee.jpa.JPATest#testSimplePersist"})
