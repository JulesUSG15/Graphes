package Exo1;

class VertexExo1 {
    int id; // Champ pour stocker l'identifiant unique du sommet.
    int[][] matrix; // Champ pour stocker une matrice 3x3.

    VertexExo1(int id, int[][] matrix) {
        this.id = id;
        this.matrix = matrix;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < matrix.length; i++) { // Boucle sur chaque ligne de la matrice.
            for (int j = 0; j < matrix[i].length; j++) { // Boucle sur chaque colonne de la ligne actuelle.
                sb.append(matrix[i][j] + " "); // Ajoute la valeur à la position (i, j) de la matrice à StringBuilder, suivie d'un espace.
            }
            sb.append("\n"); // Après chaque ligne de la matrice, ajoute un retour à la ligne
        }
        return sb.toString(); // Convertit le StringBuilder en String et le renvoie
    }

    public int getId() { // Méthode pour obtenir l'identifiant du sommet.
        return id;
    }

    
}

