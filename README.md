# gameLibrary
A full-stack web app for searching through a huge catalogue of games. Search for old or upcoming games and get live results including cover art, rating, and rating counts pulled from IGDB.

// Current Features // 
- Live game search with debounced input (no lag, no spamming the API)
- User registration and login
- JWT-based authentication — stateless, HMAC-signed tokens
- Automatic logout when a session token expires
- Game data (cover, rating, rating count) pulled from the IGDB API


// How It Works //
- User registers/logs in, backend hashes the password with BCrypt and 
   issues a signed JWT on successful login.
- The frontend stores the token and attaches it to future requests.
- Searching debounces user input, then calls the backend, which fetches 
   a cached Twitch OAuth token and queries IGDB for matching games.
- Results render as cards with cover art, rating, and rating count.

// Planned Features //
- Profile system to save added games to your profile library.
- Can rate the games and write a description below them and delete games from profile as well.

