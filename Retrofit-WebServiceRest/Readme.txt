- In this project:
	- We learn how to use Retrofit which is a library to make calls to REST web services from the simplest way on Android.
	- The use of webservices does not date from the arrival of the library Retrofit, if we break down the actions to perform well, we had:

		.Build and called URL
		.Get the content of the page
		.Analyze / Read Data
	
	There are components present since the first version of Android permettants to carry out these actions, we could:

		.Building the url with Strings
		.Use HttpClient  apache to call this URL and retrieve the contents of the page String format EntityUtils
		.Create our parsers
			.XML with Dom  or SAX
			.JSON with JsonObject
	What once finished we had already claimed three months of development and had completely unmotivated entire team of Android programming. Retrofit will do these stuffs for us.
- Reference: https://github.com/square/retrofit