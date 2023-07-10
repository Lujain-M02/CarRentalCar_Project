# CarRental_Project
- "Monjid" car renting system   
- android mobile application developed using java with basic CRUD operations and recycler view  
- the user can add, delete, rent, and return a car  
   
  </br>

## User story: 
as a user I want to be able to:  
1. add car so that the other users can view and rent it.  
1. delete My car so that other users can not view or rent it.  
1. view the car so that I can check the car information such as type, price, and other information.
2. rent a car so that other users cannot rent it.
3. return my rented car so that other users can rent it.
4. sign up so that I can access the application.
5. log in so that I can access my account.
6. log out from the system.


## ER diagram:
![image](https://github.com/Lujain-M02/CarRentalCar_Project/assets/119123675/8d384fec-09a2-4cb7-aab0-214f4d53959e)
</br>

## Class diagram:
![image](https://github.com/Lujain-M02/CarRentalCar_Project/assets/119123675/612f80e9-875e-4d09-a7cc-9e0d8623f54b)
</br>

## User Manual:
### 1.	Home page

![image](https://github.com/Lujain-M02/CarRentalCar_Project/assets/119123675/e250aa9c-9c4a-4b98-9e2b-f39afd6353a0)

Description:  
1 SIGN UP button transfer the user to signup page  
2 LOG IN button transfer the user to login page  


### 2. Login page

![image](https://github.com/Lujain-M02/CarRentalCar_Project/assets/119123675/c7420db8-26e2-4bf4-b762-a94cc5a8c16e)

Description:  
1 Input filed for User Name  
2 Input filed for Password    
3 LOG IN button transfer the user to main page  

### 3. Signup page

![image](https://github.com/Lujain-M02/CarRentalCar_Project/assets/119123675/6c121db0-548a-46eb-bc0e-afda8c891612)

Description:  
1	Input filed for User Name  
2	Input filed for Password    
3	Input filed for Email  
4	Input filed for Phone number  
5	SIGN UP button transfer the user to main page  
6	Button transfer the user to login page  

### 4.	Main page

![image](https://github.com/Lujain-M02/CarRentalCar_Project/assets/119123675/35b4756d-7b79-443d-9807-04efb4ec583a)

Description:  
1	More Button to direct the user to more page  
2	Add Button to direct the user to add car page  
3	Image and name of the car that direct the user to view page of the   specific car page if it clicked  

### 5. View page

![image](https://github.com/Lujain-M02/CarRentalCar_Project/assets/119123675/61ed6208-70d5-4cef-9a13-a604acc87d56)

Description:  
1	Button to direct the user to the previse page “mine page”  
2	Car image  
3	All car information (name, number of passengers, type, daily price)  
4	RENT Button that rents the car then direct the user to the Rent conf page  

### 6.	Rent confirmation page

![image](https://github.com/Lujain-M02/CarRentalCar_Project/assets/119123675/74c5bb98-4480-4c89-87a7-c04fb0e93e95)

Description:  
1	TextView that contains message to the user after he rent a car  
2	Home button that directs the user to main page   

### 7.	Add car page

![image](https://github.com/Lujain-M02/CarRentalCar_Project/assets/119123675/0fa3fe1a-9366-40d9-aee5-a70092efd2fe)

Description:  
1	Button to direct the user to the previse page “main page”  
2	Input field to car name  
3	Input field to number of passengers  
4	Input field to car type  
5	Input field to car daily price  
6	ImageView that the user can click and upload image   
7	Button to add the car  

### 8.	More page

![image](https://github.com/Lujain-M02/CarRentalCar_Project/assets/119123675/96a54a72-dc2b-4e6b-a214-21717e5bafde)

Description:  
1	Button to direct the user to the previse page “mine page”  
2	The username  
3	Button to logout  
4	When clicking on "MY CARS", the user will be directed to the "My Cars page ", which contains all his cars  
5	When clicking on "MY RENTAL CARS", the user will be directed to the "My Rental Cars page ", which contains all his rental cars  

### 9.	My cars page

![image](https://github.com/Lujain-M02/CarRentalCar_Project/assets/119123675/2f97be24-ce54-42d1-ac79-880f8e9a1d0c) ![image](https://github.com/Lujain-M02/CarRentalCar_Project/assets/119123675/20e20623-89e9-4915-924a-c716b07c6d00)

Description:  
1	Button to direct the user to the previse page “more page”  
2	Car name and image  
3	button to delete the car  
4	When "CANCEL" is clicked, it will not complete the car delete process  
5	When "YES" is clicked it will delete the car  

### 10.	My rental cars page

![image](https://github.com/Lujain-M02/CarRentalCar_Project/assets/119123675/112051f1-ea1f-4c62-bd9d-75dc5277ba7f) ![image](https://github.com/Lujain-M02/CarRentalCar_Project/assets/119123675/8f0a08f9-f94b-4fae-ae12-9f0d04770a38)

Description:  
1	Button to direct the user to the previse page “more page”  
2	Car name and image  
3	button to cancel the rental and return the car  
4	When "CANCL" is clicked, it will not complete the car return process  
5	When "YES" is clicked it will stop the rental and return the car  
