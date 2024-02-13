<template>
  <div v-if="currentEmployee" class="edit-form">
    <h4>Employee</h4>
    <form>
      <div class="form-group">
        <label for="name">Name</label>
        <input type="text" class="form-control" id="name"
               v-model="currentEmployee.name"
        />
      </div>
      <div class="form-group">
        <label for="note">Note</label>
        <input type="text" class="form-control" id="note"
               v-model="currentEmployee.note"
        />
      </div>
      <div class="form-group">
        <label for="profession">Profession</label>
        <input type="text" class="form-control" id="profession"
               v-model="currentEmployee.profession"
        />
      </div>
      <div class="form-group">
        <label for="department">Department</label>
        <input type="text" class="form-control" id="department"
               v-model="currentEmployee.department"
        />
      </div>

    </form>
    <button class="badge badge-danger mr-2"
            @click="deleteEmployee"
    >
      Delete
    </button>

    <button type="submit" class="badge badge-success"
            @click="updateEmployee"
    >
      Update
    </button>
    <p>{{ message }}</p>
  </div>

  <div v-else>
    <br/>
    <p>Please click on a Employee...</p>
  </div>
</template>

<script>
import EmployeeService from "@/services/EmployeeService";

export default {
  name: "employeeComponent",
  data() {
    return {
      currentEmployee: null,
      message: ''
    };
  },
  methods: {
    getEmployee(id) {
      EmployeeService.get(id)
          .then(response => {
            this.currentEmployee = response.data;
            console.log(response.data);
          })
          .catch(e => {
            console.log(e);
          });
    },

    updateEmployee() {
      EmployeeService.update(this.currentEmployee.id, this.currentEmployee)
          .then(response => {
            console.log(response.data);
            this.message = 'The employee was updated successfully!';
          })
          .catch(e => {
            console.log(e);
          });
    },
    deleteEmployee() {
      EmployeeService.delete(this.currentEmployee.id)
          .then(response => {
            console.log(response.data);
            this.$router.push({name: "employees"});
          })
          .catch(e => {
            console.log(e);
          });
    }
  },
  mounted() {
    this.message = '';
    this.getEmployee(this.$route.params.id);
  }
};
</script>

<style>
.edit-form {
  max-width: 300px;
  margin: auto;
}
</style>