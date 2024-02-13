<template>
  <div class="submit-form">
    <div v-if="!submitted">
      <div class="form-group">
        <label for="name">Name</label>
        <input
            type="text"
            class="form-control"
            id="name"
            required
            v-model="profession.name"
            name="name"
        />
      </div>

      <div class="form-group">
        <label for="note">Note</label>
        <input
            class="form-control"
            id="note"
            required
            v-model="profession.note"
            name="note"
        />
      </div>

      <button @click="saveProfession" class="btn btn-success">Submit</button>
    </div>

    <div v-else>
      <h4>You submitted successfully!</h4>
      <button class="btn btn-success" @click="newProfession">Add</button>
    </div>
  </div>
</template>

<script>
import ProfessionService from "@/services/ProfessionService";

export default {
  name: "add-profession",
  data() {
    return {
      profession: {
        id: null,
        name: "",
        note: ""
      },
      submitted: false
    };
  },
  methods: {
    saveProfession() {
      var data = {
        name: this.profession.name,
        note: this.profession.note
      };

      ProfessionService.create(data)
          .then(response => {
            this.profession.id = response.data.id;
            console.log(response.data);
            this.submitted = true;
          })
          .catch(e => {
            console.log(e);
          });
    },

    newProfession() {
      this.submitted = false;
      this.profession = {};
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