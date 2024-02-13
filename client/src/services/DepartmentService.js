import http from "../http-common";

class DepartmentService {
    getAll() {
        return http.get("/departments");
    }

    get(id) {
        return http.get(`/departments/${id}`);
    }

    create(data) {
        return http.post("/departments", data);
    }

    update(id, data) {
        return http.put(`/departments/${id}`, data);
    }

    delete(id) {
        return http.delete(`/departments/${id}`);
    }

    deleteAll() {
        return http.delete(`/departments`);
    }
    findByName(name) {
        return http.get(`/departments?name=${name}`);
    }
}

export default new DepartmentService();