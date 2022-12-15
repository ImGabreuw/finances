import dotenv from "dotenv";

dotenv.config();

import express from "express";
import { routes } from "./routes";

const app = express();

const port = process.env.PORT || 3000;

app.listen(port, () => {
 console.log(`listening on port ${port}`);
});

app.use(express.json());
app.use(routes);
