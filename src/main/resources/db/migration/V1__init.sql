-- Users table
CREATE TABLE users (
                       id VARCHAR(50) PRIMARY KEY,
                       full_name VARCHAR(200) NOT NULL,
                       email VARCHAR(255) UNIQUE NOT NULL,
                       mobile VARCHAR(30),
                       role VARCHAR(50) DEFAULT 'citizen',
                       password_hash VARCHAR(255) NOT NULL
);

-- Complaint categories
CREATE TABLE complaint_categories (
                                      id SERIAL PRIMARY KEY,
                                      name VARCHAR(100) UNIQUE NOT NULL,
                                      default_radius INT NOT NULL
);

-- Complaints
CREATE TABLE complaints (
                            id VARCHAR(50) PRIMARY KEY,
                            reporter_id VARCHAR(50),
                            category_id INT NOT NULL,
                            title VARCHAR(200) NOT NULL,
                            description TEXT,
                            image_url VARCHAR(500),
                            latitude DECIMAL(9,6),
                            longitude DECIMAL(9,6),
                            radius_meters INT NOT NULL,
                            status VARCHAR(50) DEFAULT 'open',
                            upvote_count INT DEFAULT 0,
                            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                            FOREIGN KEY (reporter_id) REFERENCES users(id),
                            FOREIGN KEY (category_id) REFERENCES complaint_categories(id)
);

-- Complaint upvotes
CREATE TABLE complaint_upvotes (
                                   complaint_id VARCHAR(50),
                                   user_id VARCHAR(50),
                                   PRIMARY KEY (complaint_id, user_id),
                                   FOREIGN KEY (complaint_id) REFERENCES complaints(id),
                                   FOREIGN KEY (user_id) REFERENCES users(id)
);