package com.example.carecareforeldres.Service;

import com.example.carecareforeldres.Entity.Ingredient;
import com.example.carecareforeldres.Entity.Maladie;
import com.example.carecareforeldres.Repository.IngredientRepository;
import lombok.AllArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class EscelUploadService {

    private final MaladieService maladieService; // Injecter le service MaladieService
    private final IngredientRepository ingredientRepository;
    public static boolean isValidExcelFile(MultipartFile file) {
        return Objects.equals(file.getContentType(), "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    }

    public List<Ingredient> getCustomersDataFromExcel(InputStream inputStream) throws IOException {
        List<Ingredient> ingredients = new ArrayList<>();
        try (XSSFWorkbook workbook = new XSSFWorkbook(inputStream)) {
            Sheet sheet = workbook.getSheet("customers");
            if (sheet == null) {
                throw new IllegalArgumentException("La feuille de calcul 'customers' n'a pas été trouvée dans le fichier Excel.");
            }

            int rowIndex = 0;
            for (Row row : sheet) {
                if (rowIndex == 0) {
                    rowIndex++;
                    continue; // Skip header row
                }
                Iterator<Cell> cellIterator = row.iterator();
                int cellIndex = 0;
                Ingredient ingredient = new Ingredient();
                ingredient.setDateAjout(LocalDateTime.now());
                List<Maladie> maladies = new ArrayList<>(); // Liste de maladies pour cet ingrédient
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    switch (cellIndex) {
                        case 0:
                            ingredient.setNomIngredient(cell.getStringCellValue());
                            break;
                        case 1:
                            float calorie = (float) cell.getNumericCellValue();
                            ingredient.setCalorie(calorie);
                            break;
                        case 2:
                            int quantite = (int) cell.getNumericCellValue();
                            ingredient.setQuantite(quantite);
                            break;
                        case 3:
                            boolean consommable = cell.getBooleanCellValue();
                            ingredient.setConsommable(consommable);
                            break;
                        case 4:
                            String maladiesString = cell.getStringCellValue();
                            String[] maladiesArray = maladiesString.split(",");
                            for (String maladieName : maladiesArray) {
                                Maladie maladie = maladieService.findOrCreateMaladie(maladieName.trim());
                                maladies.add(maladie);
                            }
                            break;
                        default:
                            break;
                    }
                    cellIndex++;
                }
                ingredient.setMaladies(maladies); // Définir la liste de maladies pour cet ingrédient
                ingredients.add(ingredient);
            }
        }
        return ingredients;
    }

}
