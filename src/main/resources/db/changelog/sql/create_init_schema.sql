CREATE TABLE transactions (
    id bigint NOT NULL,
    amount double precision NOT NULL,
    txn_id bigint NOT NULL UNIQUE,
    parent_id bigint,
    type character varying(255) NOT NULL
);
CREATE INDEX type_index ON transactions (type);

CREATE SEQUENCE transactions_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE transactions_id_seq OWNED BY transactions.id;
ALTER TABLE ONLY transactions ALTER COLUMN id SET DEFAULT nextval('transactions_id_seq'::regclass);

CREATE TABLE mapping_txns (
    id bigint NOT NULL,
    parent_id bigint NOT NULL,
    child_id bigint NOT NULL,
    depth integer NOT NULL DEFAULT 0,
    CONSTRAINT unq_parent_child UNIQUE(parent_id,child_id)
);
--
CREATE INDEX parent_index ON mapping_txns (parent_id);

CREATE SEQUENCE mapping_txns_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE mapping_txns_id_seq OWNED BY mapping_txns.id;
ALTER TABLE ONLY mapping_txns ALTER COLUMN id SET DEFAULT nextval('mapping_txns_id_seq'::regclass);