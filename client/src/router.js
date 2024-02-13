import {createWebHistory, createRouter} from "vue-router";

const routes = [
    {
        path: "/",
        alias: "/professions",
        name: "professions",
        component: () => import("./components/professions/ProfessionsList")
    },
    {
        path: "/professions/:id",
        name: "profession-details",
        component: () => import("./components/professions/Profession")
    },
    {
        path: "/addProfession",
        name: "addProfession",
        component: () => import("./components/professions/AddProfession")
    },
    {
        path: "/",
        alias: "/departments",
        name: "departments",
        component: () => import("./components/departments/DepartmentsList.vue")
    },
    {
        path: "/departments/:id",
        name: "department-details",
        component: () => import("./components/departments/Department")
    },
    {
        path: "/addDepartment",
        name: "addDepartment",
        component: () => import("./components/departments/AddDepartment")
    },
    {
        path: "/",
        alias: "/employees",
        name: "employees",
        component: () => import("./components/employees/EmployeesList")
    },
    {
        path: "/employees/:id",
        name: "employee-details",
        component: () => import("./components/employees/Employee.vue")
    },
    {
        path: "/addEmployee",
        name: "addEmployee",
        component: () => import("./components/employees/AddEmployee.vue")
    },

];
const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;