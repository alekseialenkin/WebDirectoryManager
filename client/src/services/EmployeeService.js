import http from "../http-common";

class EmployeeService {
    getAll() {
        return http.get("/employees");
    }

    get(id) {
        return http.get(`/employees/${id}`);
    }

    create(data) {
        return http.post("/employees", data);
    }

    update(id, data) {
        return http.put(`/employees/${id}`, data);
    }

    delete(id) {
        return http.delete(`/employees/${id}`);
    }

    findByName(name) {
        return http.get(`/employees?name=${name}`);
    }
}

export default new EmployeeService();