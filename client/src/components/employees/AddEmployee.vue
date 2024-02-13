<template>
  <div class="submit-form">
    <div v-if="!submitted">
      <div class="form-group">
        <label for="name">Full Name</label>
        <input
            type="text"
            class="form-control"
            id="name"
            required
            v-model="employee.name"
            name="name"
        />
      </div>

      <div class="form-group">
        <label for="note">Note</label>
        <input
            class="form-control"
            id="note"
            required
            v-model="employee.note"
            name="note"
        />
      </div>

      <div class="form-group">
        <label for="profession">Profession</label>
        <input
            class="form-control"
            id="profession"
            required
            v-model="employee.profession"
            name="profession"
        />
      </div>

      <div class="form-group">
        <label for="department">Department</label>
        <input
            class="form-control"
            id="department"
            required
            v-model="employee.department"
            name="department"
        />
      </div>

      <button @click="saveEmployee" class="btn btn-success">Submit</button>
    </div>

    <div v-else>
      <h4>You submitted successfully!</h4>
      <button class="btn btn-success" @click="newEmployee">Add</button>
    </div>
  </div>
</template>

<script>
import EmployeeService from "@/services/EmployeeService";

export default {
  name: "add-employee",
  data() {
    return {
      employee: {
        id: null,
        name: "",
        note: "",
        profession: "",
        department: ""
      },
      submitted: false
    };
  },
  methods: {
    saveEmployee() {
      var data = {
        name: this.employee.name,
        note: this.employee.note,
        profession: this.employee.profession,
        department: this.employee.department
      };

      EmployeeService.create(data)
          .then(response => {
            this.employee.id = response.data.id;
            console.log(response.data);
            this.submitted = true;
          })
          .catch(e => {
            console.log(e);
          });
    },

    newEmployee() {
      this.submitted = false;
      this.employee = {};
    }
  }
};
</script>

<style>
.submit-form {
  max-width: 300px;
  margin: auto;
}
</style>