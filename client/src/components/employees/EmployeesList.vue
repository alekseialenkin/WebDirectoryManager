<template>
  <div class="list row">
    <div class="col-md-8">
      <div class="input-group mb-3">
        <input type="text" class="form-control" placeholder="Search by name"
               v-model="name"/>
        <div class="input-group-append">
          <button class="btn btn-outline-secondary" type="button"
                  @click="searchEmployee"
          >
            Search
          </button>
        </div>
      </div>
    </div>
    <div class="col-md-6">
      <h4>Employees List</h4>
      <ul class="list-group">
        <li class="list-group-item"
            :class="{ active: index == currentIndex }"
            v-for="(employee, index) in employees"
            :key="index"
            @click="setActiveEmployee(employee, index)"
        >
          {{ employee.name }}
        </li>
      </ul>
      <div class="nav-item">
        <router-link to="/addEmployee" class="nav-link">Add</router-link>
      </div>
    </div>
    <div class="col-md-6">
      <div v-if="currentEmployee">
        <h4>Employee</h4>
        <div>
          <label><strong>Full Name:</strong></label> {{ currentEmployee.name }}
        </div>
        <div>
          <label><strong>Note:</strong></label> {{ currentEmployee.note }}
        </div>

        <div>
          <label><strong>Profession:</strong></label> {{ currentEmployee.profession }}
        </div>

        <div>
          <label><strong>Department:</strong></label> {{ currentEmployee.department }}
        </div>
        <router-link :to="'/employees/' + currentEmployee.id" class="badge badge-warning">Edit</router-link>
      </div>
      <div v-else>
        <br/>
        <p>Please click on a Employee...</p>
      </div>
    </div>
  </div>
</template>

<script>
import EmployeeService from "@/services/EmployeeService";
export default {
  name: "employee-list",
  data() {
    return {
      employees: [],
      currentEmployee: null,
      currentIndex: -1,
      title: ""
    };
  },
  methods: {
    retrieveEmployees() {
      EmployeeService.getAll()
          .then(response => {
            this.employees = response.data;
            console.log(response.data);
          })
          .catch(e => {
            console.log(e);
          });
    },

    setActiveEmployee(employee, index) {
      this.currentEmployee = employee;
      this.currentIndex = employee ? index : -1;
    },

    searchEmployee() {
      EmployeeService.findByName(this.title)
          .then(response => {
            this.employees = response.data;
            this.setActiveEmployee(null);
            console.log(response.data);
          })
          .catch(e => {
            console.log(e);
          });
    }
  },
  mounted() {
    this.retrieveEmployees();
  }
};
</script>

<style>
.list {
  text-align: left;
  max-width: 750px;
  margin: auto;
}
</style>