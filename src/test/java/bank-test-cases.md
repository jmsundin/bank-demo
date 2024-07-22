# Bank System Test Cases

## Use Cases

ID: 1
Name: User registration
Description: Users should be able to register with the bank
System: Bank application
Preconditions:
- Username is unique
- Username and password must be less than 30 characters
Actors:
- User (creating a new account)
- Bank application (handles the creation of the new account)

ID: 2
Name: User login
Description: User can securely access/login to bank application
System: Bank application
Preconditions:
- User has registered
Actors:
- User (logging into the bank application)
- Bank application (handles the login of the user)

ID: 3
Name: Deposit of funds
Description: User can deposit funds into any owned account
System: Bank application
Preconditions:
- User has created a checking account
Actors:
- User (depositing funds into their checking account)
- Banking application (handles the deposit of funds into the checking account)

ID: 4
Name: Withdrawal of funds
Description: User can withdrawal funds from any owned account
System: Bank application
Preconditions:
- User has adequate funds in checking account
Actors:
- User (withdrawing funds from their account)
- Banking application (handles the withdrawal of funds from the account)

ID: 5
Name: New checking account
Description: User can create one or more checking accounts
System: Bank application
Preconditions:
- N/A
Actors:
- User (creating a new checking account)
- Banking application (handles the creation of the new checking account)

ID: 6
Name: Checking account details
Description: User can view details of any checking account they own
System: Bank application
Preconditions:
- User has created one or more checking accounts
Actors:
- User (viewing details of any checking account they own)
- Banking application (handles the viewing of the details of the checking account)

ID: 7
Name: Closing checking account
Description: User can close one or more checking accounts
System: Bank application
Preconditions:
- N/A
Actors:
- User (closing a checking account)
- Banking application (handles the closing of the checking account)

## Positive Scenarios

ID: 1
Name: User registration
1. User starts bank application in the terminal -- seeing the landing page screen
2. User enters appropriate key to register with the bank
3. User is prompted to enter a username, and enters a valid username
4. User is prompted to enter a password, and enters a valid password
5. User account is created
6. User is logged into the bank application

ID: 2
Name: User login
1. User starts bank application in the terminal -- seeing the landing page screen
2. User enters appropriate key to login to the bank
3. User is prompted to enter a username, and enters a valid username
4. User is prompted to enter a password, and enters a valid password
5. User is logged into the bank application

ID: 3
Name: Deposit of funds
1. User logs into the bank application with valid credentials
2. User enters appropriate key to view a specific checking account they own
3. User enters appropriate key to start deposit process
4. User enters deposit amount
5. Deposit is successful

ID: 4
Name: Withdrawal of funds
1. User logs into the bank application with valid credentials
2. User enters appropriate key to view a specific checking account they own
3. User enters appropriate key to start withdrawal process
4. User enters withdrawal amount
5. Withdrawal is successful

ID: 5
Name: New checking account
1. User logs into the bank application with valid credentials
2. User enters appropriate key to create a new checking account
3. New checking account is created

ID: 6
Name: Checking account details
1. User logs into the bank application with valid credentials
2. User enters appropriate key to view a specific checking account they own
3. Checking account details are displayed to user

ID: 7
Name: Closing checking account
1. User logs into the bank application with valid credentials
2. User enters appropriate key to view a specific checking account they own
3. User enters appropriate key to close the checking account
4. User is prompted to confirm closing of the checking account
5. Checking account is closed

## Positive Use Case Scenario Data

ID: 1
Name: User registration
- valid username = "jon"
- valid password = "jon"

ID: 2
Name: User login
- valid username = "jon"
- valid password = "jon"

ID: 3
Name: Deposit of funds
- valid username = "jon"
- valid password = "jon"
- key presses = "1 + ENTER" (to view checking accounts)
- key presses = "1 + ENTER" (to view first checking account details)
- key presses = "1 + ENTER" (to start deposit process)
- key presses = "100 + ENTER" (to deposit $100 amount)

ID: 4
Name: Withdrawal of funds
- valid username = "jon"
- valid password = "jon"
- key presses = "1 + ENTER" (to view checking accounts)
- key presses = "1 + ENTER" (to view first checking account details)
- key presses = "2 + ENTER" (to start withdrawal process)
- key presses = "100 + ENTER" (to withdrawal $100 amount)

ID: 5
Name: New checking account
- valid username = "jon"
- valid password = "jon"
- key presses = "2 + ENTER" (to create a new checking account)

ID: 6
Name: Checking account details
- valid username = "jon"
- valid password = "jon"
- key presses = "1 + ENTER" (to view checking accounts)
- key presses = "1 + ENTER" (to view first checking account details)

ID: 7
Name: Closing checking account
- valid username = "jon"
- valid password = "jon"
- key presses = "1 + ENTER" (to view checking accounts)
- key presses = "1 + ENTER" (to view first checking account details)
- key presses = "CLOSE ACCOUNT + ENTER" (to close the checking account)
- key presses = "YES + ENTER" (to confirm closing of the checking account)

## Negative Use Case Scenarios

ID: 1
Name: User registration
1. User starts bank application in the terminal -- seeing the landing page screen
2. User enters appropriate key to register with the bank
3. User is prompted to enter a username, and enters a non-unique username
4. User is prompted to enter a password, and enters a valid password
5. User is informed that the username is already taken

ID: 1
Name: User registration
1. User starts bank application in the terminal -- seeing the landing page screen
2. User enters appropriate key to register with the bank
3. User is prompted to enter a username, and enters a username that is too long
4. User is prompted to enter a password, and enters a valid password
5. User is informed that the username is too long

ID: 1
Name: User registration
1. User starts bank application in the terminal -- seeing the landing page screen
2. User enters appropriate key to register with the bank
3. User is prompted to enter a username, and enters a valid username
4. User is prompted to enter a password, and enters a password that is too long
5. User is informed that the password is too long

ID: 2
Name: User login
1. User starts bank application in the terminal -- seeing the landing page screen
2. User enters appropriate key to login to the bank
3. User is prompted to enter a username, and enters a username that does not exist
4. User is prompted to enter a password, and enters a valid password
5. User is informed that the username does not exist

ID: 2
Name: User login
1. User starts bank application in the terminal -- seeing the landing page screen
2. User enters appropriate key to login to the bank
3. User is prompted to enter a username, and enters a valid username
4. User is prompted to enter a password, and enters the wrong password
5. User is informed that the password was incorrect

ID: 3
Name: Deposit of funds
1. User starts bank application in the terminal -- seeing the landing page screen
2. User enters appropriate key to login to the bank
3. User is prompted to enter a username and enters a valid username
4. User is prompted to enter a password and enters a valid password
5. User enters appropriate key to view checking accounts
6. User enters appropriate key to view first checking account details
7. User enters appropriate key to start deposit process
8. User is prompted to enter an amount to deposit
9. System malfunctions and deposit is unsuccessful

ID: 4
Name: Withdrawal of funds
1. User starts bank application in the terminal -- seeing the landing page screen
2. User enters appropriate key to login to the bank
3. User is prompted to enter a username, and enters a username that does not exist
4. User is prompted to enter a password, and enters a valid password
5. User is informed that the username does not exist

## Negative Use Case Scenario Data

ID: 1
Name: User registration


## Test Cases

ID:
Description:
Preconditions:
Test Data:
Steps:
Expected Outcome:
Actual Outcome:
Who executed the test:
Status: