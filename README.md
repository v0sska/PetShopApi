This project was writed in Java 17.0.10 and SpringBoot 3.2.5
Swagger file is located in resources/static folder.
You can upload xml or csv file for to fill database by multipart file.

Example:

Input xml file:

      <animals>
	<animal>
		<name>Milo</name>
		<type>cat</type>
		<sex>male</sex>
		<weight>40</weight>
		<cost>51</cost>
	</animal>
     </animals>

Class Animals and Animal used for writting data from file.Class PetsPojo is used for transfer data from parsers to main entity.

Output data in database:

        {
            "id": 40,
            "name": "Milo",
            "type": "cat",
            "sex": "male",
            "weight": 40,
            "cost": 51,
            "category": 3
        }

Category is calculates by cost.
All fields is must be not empty.If at least one field is empty object will be ignored.
