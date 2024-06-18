-- Table: Cinema
CREATE DATABASE horizon_cinemas;
-- Table: Cinema
CREATE TABLE cinemas (
                         CinemaID INTEGER PRIMARY KEY AUTO_INCREMENT,
                         CinemaName TEXT NOT NULL,
                         Address TEXT NOT NULL
);

-- Table: Screen
CREATE TABLE screens (
                         ScreenID INTEGER PRIMARY KEY AUTO_INCREMENT,
                         CinemaID INTEGER REFERENCES cinemas(CinemaID),
                         ScreenName TEXT NOT NULL,
                         TotalSeats INTEGER NOT NULL CHECK (TotalSeats > 0),
                         LowerHallSeats INTEGER DEFAULT 0 CHECK (LowerHallSeats >= 0),
                         UpperGallerySeats INTEGER DEFAULT 0 CHECK (UpperGallerySeats >= 0),
                         VIPSeats INTEGER DEFAULT 0 CHECK (VIPSeats >= 0)
);

-- Table: Film
CREATE TABLE films (
                       FilmID INTEGER PRIMARY KEY AUTO_INCREMENT,
                       Title TEXT NOT NULL,
                       Genre TEXT,
                       AgeRating TEXT,
                       Description TEXT
);

-- Table: Show
CREATE TABLE shows (
                       ShowID INTEGER PRIMARY KEY AUTO_INCREMENT,
                       FilmID INTEGER REFERENCES films(FilmID),
                       ScreenID INTEGER REFERENCES screens(ScreenID),
                       ShowDate TEXT NOT NULL,
                       ShowTime TEXT NOT NULL
);

-- Table: User
CREATE TABLE users (
                       UserID INTEGER PRIMARY KEY AUTO_INCREMENT,
                       UserType TEXT NOT NULL CHECK (UserType IN ('Administrator', 'manager', 'customer')),
                       Password TEXT NOT NULL,
                       EmployeeID TEXT UNIQUE
);

-- Table: Booking
CREATE TABLE bookings (
                          BookingID INTEGER PRIMARY KEY AUTO_INCREMENT,
                          UserID INTEGER REFERENCES users(UserID),
                          ShowID INTEGER REFERENCES shows(ShowID),
                          FilmID INTEGER REFERENCES films(FilmID),
                          ShowDate TEXT NOT NULL,
                          BookingDate TEXT DEFAULT CURRENT_TIMESTAMP
);

-- Table: Ticket
CREATE TABLE tickets (
                         TicketID INTEGER PRIMARY KEY AUTO_INCREMENT,
                         ShowID INTEGER REFERENCES shows(ShowID),
                         Price REAL,
                         SeatNumber TEXT NOT NULL,
                         CONSTRAINT unique_seat_per_show UNIQUE (ShowID, SeatNumber)
);
