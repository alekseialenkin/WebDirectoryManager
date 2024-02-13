import http from "../http-common";

class ProfessionService {
    getAll() {
        return http.get("/professions");
    }

    get(id) {
        return http.get(`/professions/${id}`);
    }

    create(data) {
        return http.post("/professions", data);
    }

    update(id, data) {
        return http.put(`/professions/${id}`, data);
    }

    delete(id) {
        return http.delete(`/professions/${id}`);
    }

    findByName(name) {
        return http.get(`/professions?name=${name}`);
    }
}

export default new ProfessionService();