# Project182
1. The problem domain our team is tentatively wanting to focus on Recipe Organizer.

2. For Recipe Organizer, the applications our team is thinking of developing are as follows:
   - store recipes (ingredients, instructions, etc.): save important information of a recipe to the recipe base.
   - organize recipes in some meaningful way, such as alphabetically or by the most viewed recipe...
   - user customization: show a list of the user's favorite recipes or most used recipes, (recommend recipes a user may like by the user's searches if possible).
   - write comments  and likes for recipes: collect the number of likes and all comments other users made for a recipe.
   - api.service.signup.search for the recipe by ingredient, calories, diet or allergies: show all recipes with the specific searching word or ingredient.

3. The link to the documentation for an API that our team can use related to the domain is as follows:
- Recipe database:
   - The MealDB: https://www.themealdb.com/api.php.
   - Edamam: https://www.edamam.com/results/recipes/?search=salad.

5. A screenshot of using a tool to try out the API:
   ![image](https://github.com/Sharon-zh/Project182/blob/main/screenshot.png?raw=true)

6. The example output of running your Java code:
   
Response{protocol=h2, code=200, message=, url=https://www.themealdb.com/api/json/v1/1/search.php?lat=%7Blat%7D&s=Arrabiata}
```
{"meals":[{"strImageSource":null,"strIngredient10":"","strIngredient12":"","strIngredient11":"","strIngredient14":"","strCategory":"Vegetarian","strIngredient13":"","strIngredient16":null,"strIngredient15":"","strIngredient18":null,"strIngredient17":null,"strArea":"Italian","strCreativeCommonsConfirmed":null,"strIngredient19":null,"strTags":"Pasta,Curry","idMeal":"52771","strInstructions":"Bring a large pot of water to a boil. Add kosher salt to the boiling water, then add the pasta. Cook according to the package instructions, about 9 minutes.\r\nIn a large skillet over medium-high heat, add the olive oil and heat until the oil starts to shimmer. Add the garlic and cook, stirring, until fragrant, 1 to 2 minutes. Add the chopped tomatoes, red chile flakes, Italian seasoning and salt and pepper to taste. Bring to a boil and cook for 5 minutes. Remove from the heat and add the chopped basil.\r\nDrain the pasta and add it to the sauce. Garnish with Parmigiano-Reggiano flakes and more basil and serve warm.","strIngredient1":"penne rigate","strIngredient3":"garlic","strIngredient2":"olive oil","strIngredient20":null,"strIngredient5":"red chile flakes","strIngredient4":"chopped tomatoes","strIngredient7":"basil","strIngredient6":"italian seasoning","strIngredient9":"","strIngredient8":"Parmigiano-Reggiano","strMealThumb":"https://www.themealdb.com/images/media/meals/ustsqw1468250014.jpg","strMeasure20":null,"strYoutube":"https://www.youtube.com/watch?v=1IszT_guI08","strMeal":"Spicy Arrabiata Penne","strMeasure12":"","strMeasure13":"","strMeasure10":"","strMeasure11":"","dateModified":null,"strDrinkAlternate":null,"strSource":null,"strMeasure9":"","strMeasure7":"6 leaves","strMeasure8":"spinkling","strMeasure5":"1/2 teaspoon","strMeasure6":"1/2 teaspoon","strMeasure3":"3 cloves","strMeasure4":"1 tin ","strMeasure1":"1 pound","strMeasure18":null,"strMeasure2":"1/4 cup","strMeasure19":null,"strMeasure16":null,"strMeasure17":null,"strMeasure14":"","strMeasure15":""}]}
```

Process finished with exit code 0


7. The list of the technical problems blocking progress:
- We  don’t understand how to use the full HTTP Methods.
- We don’t understand the meaning for API Java code line by line.

8. Use case:
- Sign up
- Log in
- Log out
- Search by ingredient, calories, diet or allergies
- Upload Recipe (words, photos and videos)
- ?Edit Recipe (words, photos and videos)
- Store Recipe
- Write comments
- Give likes
- Organize recipes alphabetically
- Organize recipes by the most viewed recipe
- Recommend (by searching history, favorite recipes or top ten recipes)
  
9. Next step:
- Refine specification
- Draw a UML class diagram and UML sequence diagram for a core use case
- Sketch out what the view will look like
- Think more about what API we will be using and how we will be using it for your project
- Agree on and draft a set of entities based on the problem domain
- Pick a simple entity and write a DAO
- Choose a user interaction, draw a picture of what the UI will look like (Java Swing)
- For each of your user stories, make a user interface
- Go over your user stories and figure out what those user actions
  
10. Implemention documentation:
  - service.signup.search, login: Teresa;
  - recommendation, like: Ruby;
  - store, log out: Sharon;
  - service.comment, sign up: Kevin;
  
11. Question:
  - InmemoryDAO vs FileDAO?
  - How to use API?
  - How the data from API is represented in our program?
  - What is API key?

12. Plan:
- 11.13-11.19: finish 8 use cases and 2 tests per use case (controller to presenter, DAO)
- 11.20-11.26: finish 1 test and view for each use case (finish the coding part of the project)
     * Kevin: signup view, main, commentUseCaseFactory, signupUseCaseFactory, load favorite recipe UseCaseFactory, recommend view
     * Ruby: login view, remove favorite recipe UseCaseFactory, logoutUseCaseFactory, likeUseCaseFactory, recommendUseCaseFactory, recipe view
     * Teresa: save favorite recipe UseCaseFactory, searchUseCaseFactory, after_searchUseCaseFactory, loginUseCaseFactory, search result view
     * Sharon: search view
- 11.27-12.3: slides



