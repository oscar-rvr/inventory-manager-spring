CREATE TABLE IF NOT EXISTS vendors (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    contact VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS purchases (
    id SERIAL PRIMARY KEY,
    vendor_id INTEGER NOT NULL,
    date DATE NOT NULL,
    total_amount NUMERIC(10,2) NOT NULL,
    CONSTRAINT fk_purchases_vendor FOREIGN KEY (vendor_id) REFERENCES vendors(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS assets (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    series_number VARCHAR(255) UNIQUE
);

CREATE TABLE IF NOT EXISTS purchase_details (
    id SERIAL PRIMARY KEY,
    purchase_id INTEGER NOT NULL,
    asset_id INTEGER NOT NULL,
    amount INTEGER NOT NULL,
    price_per_item NUMERIC(10,2) NOT NULL,
    CONSTRAINT fk_purchase_details_purchase FOREIGN KEY (purchase_id) REFERENCES purchases(id) ON DELETE CASCADE,
    CONSTRAINT fk_purchase_details_asset FOREIGN KEY (asset_id) REFERENCES assets(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS employees (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    mail VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    employee_id INTEGER NOT NULL UNIQUE,
    role VARCHAR(50) NOT NULL,
    username VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    CONSTRAINT fk_users_employee FOREIGN KEY (employee_id) REFERENCES employees(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS assets_movements (
    id SERIAL PRIMARY KEY,
    employee_id INTEGER NOT NULL,
    asset_id INTEGER NOT NULL,
    movement_type VARCHAR(50) NOT NULL CHECK (movement_type IN ('ASSIGN', 'TRANSFER', 'RETURN')),
    asset_movement_date DATE NOT NULL,
    CONSTRAINT fk_assets_movements_employee FOREIGN KEY (employee_id) REFERENCES employees(id) ON DELETE SET NULL,
    CONSTRAINT fk_assets_movements_asset FOREIGN KEY (asset_id) REFERENCES assets(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS computers (
    id SERIAL PRIMARY KEY,
    asset_id INTEGER NOT NULL UNIQUE,
    ram INTEGER NOT NULL,
    disk INTEGER NOT NULL,
    core VARCHAR(50) NOT NULL,
    screen_state VARCHAR(50) NOT NULL,
    keyboard_state VARCHAR(50) NOT NULL,
    shell_state VARCHAR(50) NOT NULL,
    comments TEXT,
    CONSTRAINT fk_computers_asset FOREIGN KEY (asset_id) REFERENCES assets(id) ON DELETE CASCADE
);

-- Indexes for performance optimization
CREATE INDEX IF NOT EXISTS idx_purchases_id ON purchases(id);
CREATE INDEX IF NOT EXISTS idx_employees_id ON employees(id) include (name);
CREATE INDEX IF NOT EXISTS idx_computers_id ON computers(asset_id);
CREATE INDEX IF NOT EXISTS idx_assets_id ON assets(id);
CREATE INDEX IF NOT EXISTS idx_assets_movements_ids ON assets_movements(asset_id, employee_id);