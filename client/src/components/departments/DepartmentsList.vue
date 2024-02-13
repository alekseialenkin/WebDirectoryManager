<template>
  <div class="list row">
    <div class="col-md-8">
      <div class="input-group mb-3">
        <input type="text" class="form-control" placeholder="Search by name"
               v-model="name"/>
        <div class="input-group-append">
          <button class="btn btn-outline-secondary" type="button"
                  @click="searchDepartment"
          >
            Search
          </button>
        </div>
      </div>
    </div>
    <div class="col-md-6">
      <h4>Departments List</h4>
      <ul class="list-group">
        <li class="list-group-item"
            :class="{ active: index == currentIndex }"
            v-for="(department, index) in departments"
            :key="index"
            @click="setActiveDepartment(department, index)"
        >
          {{ department.name }}
        </li>
      </ul>
      <div class="nav-item">
        <router-link to="/addDepartment" class="nav-link">Add</router-link>
      </div>
    </div>
    <div class="col-md-6">
      <div v-if="currentDepartment">
        <h4>Department</h4>
        <div>
          <label><strong>Name:</strong></label> {{ currentDepartment.name }}
        </div>
        <div>
          <label><strong>Note:</strong></label> {{ currentDepartment.note }}
        </div>

        <router-link :to="'/departments/' + currentDepartment.id" class="badge badge-warning">Edit</router-link>
      </div>
      <div v-else>
        <br/>
        <p>Please click on a Department...</p>
      </div>
    </div>
  </div>
</template>

<script>
import DepartmentService from "@/services/DepartmentService";

export default {
  name: "departments-list",
  data() {
    return {
      departments: [],
      currentDepartment: null,
      currentIndex: -1,
      title: ""
    };
  },
  methods: {
    retrieveDepartments() {
      DepartmentService.getAll()
          .then(response => {
            this.departments = response.data;
            console.log(response.data);
          })
          .catch(e => {
            console.log(e);
          });
    },

    setActiveDepartment(department, index) {
      this.currentDepartment = department;
      this.currentIndex = department ? index : -1;
    },

    searchDepartment() {
      DepartmentService.findByName(this.title)
          .then(response => {
            this.departments = response.data;
            this.setActiveDepartment(null);
            console.log(response.data);
          })
          .catch(e => {
            console.log(e);
          });
    }
  },
  mounted() {
    this.retrieveDepartments();
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