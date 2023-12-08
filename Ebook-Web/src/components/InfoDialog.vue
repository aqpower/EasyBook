<script setup lang="ts">
import { TransitionRoot, TransitionChild, Dialog, DialogPanel, DialogTitle } from '@headlessui/vue'

// 声明 props
const props = defineProps({
  title: String,
  content: String,
  isOpen: Boolean,
  close: {
    type: Function,
    default: (fun: () => any) => fun()
  }
})

const emits = defineEmits(['update:isOpen'])
function closeModal() {
  emits('update:isOpen', false)
}
</script>

<template>
  <TransitionRoot appear :show="isOpen" as="template">
    <Dialog as="div" @close="closeModal" class="relative z-10">
      <TransitionChild
        as="template"
        enter="duration-300 ease-out"
        enter-from="opacity-0"
        enter-to="opacity-100"
        leave="duration-200 ease-in"
        leave-from="opacity-100"
        leave-to="opacity-0"
      >
        <div class="fixed inset-0 bg-black/25" />
      </TransitionChild>

      <div class="fixed inset-0 overflow-y-auto">
        <div class="flex min-h-full items-center justify-center p-4 text-center">
          <TransitionChild
            as="template"
            enter="duration-300 ease-out"
            enter-from="opacity-0 scale-95"
            enter-to="opacity-100 scale-100"
            leave="duration-200 ease-in"
            leave-from="opacity-100 scale-100"
            leave-to="opacity-0 scale-95"
          >
            <DialogPanel
              class="w-full max-w-md transform overflow-hidden rounded-2xl bg-white p-6 text-left align-middle shadow-xl transition-all"
            >
              <DialogTitle class="text-xl font-medium leading-9 text-gray-900">
                {{ props.title }}
              </DialogTitle>
              <div class="mt-2">
                <p class="text-base text-gray-500">
                  {{ props.content }}
                </p>
              </div>

              <div class="mt-4 flex justify-end">
                <button
                  type="button"
                  class="inline-flex rounded-md border border-transparent bg-rose-100 px-4 py-2 text-sm font-medium text-blue-900 hover:bg-rose-200 focus:outline-none focus-visible:ring-2 focus-visible:ring-rose-500 focus-visible:ring-offset-2"
                  @click="close(closeModal)"
                >
                  👌
                </button>
              </div>
            </DialogPanel>
          </TransitionChild>
        </div>
      </div>
    </Dialog>
  </TransitionRoot>
</template>
