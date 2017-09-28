News Reader – Android App
==========
This android application parses Rest api feeds and displays the contents into an easy to read and unified place.
Clicking an article also displays the view, allowing the user to easily read it.
Simple News Reader supports an internal database (Realm) to store and cache feeds and articles.

Getting Started
==========
```
• The data should be pulled from http://open-platform.theguardian.com/documentation/search, using JSON format. The articles should include a title, an image and category.
• The home page should be an infinite scrolling list of the articles (optional: ability to switch from list view to Pinterest-like view). Scrolling down the list should pull the next items from the feed.
• Tapping an article should open it in a new page with shared element transition of the image.
• Ability to save the article for offline use. The app should be able to open the offline items without network connection.
• Ability to Pin items to the home page view using an icon in the article view page. The pinned items should be listed in a horizontal scrolling view on top of the home page list. When pinning an article, the new item should be reflected in the list dynamically, upon going back to the home page.
• The app should check for new items in the feed and add them to the list every 30 seconds.
```
Sample app
==========
[![Sample app](https://img.youtube.com/vi/gBpSbycTuxI/2.jpg)](https://youtu.be/gBpSbycTuxI)

Features
==========
1. Used MVP architecture
2. Used popular third party libraries, such as: Gson, Realm, Retrofit, Picasso and Butterknife
3. Used mockito test methods to test applicarion
4. Used Repository Design Pattern

How it Works
==========
1. View(Activity, Fragment, ...) calls presenter methods whenever there're user interaction
2. Presenter implementation calls the interactor(use case handler) to get results from business/domain layer
3. Interactor implementation returns the results or just returns the control to presenter implementation by calling listener methods
4. Presenter implementation calls view methods to update the UI by calling view interface.


Android versions
==========
Min Sdk Version 17 <br/>
Target Sdk Version 25 <br/>
Compile Sdk Version 25

Developed By
==========
David Galstyan
